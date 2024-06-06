package ru.baydak.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import ru.baydak.entity.Chat;
import ru.baydak.entity.UserChat;

public class UserChatListener {

    @PostPersist
    public void postPersist(UserChat userChat) {
        Chat chat = userChat.getChat();
        chat.setCount(chat.getCount() + 1);
    }

    @PostRemove
    public void postRemove(UserChat userChat) {
        Chat chat = userChat.getChat();
        chat.setCount(chat.getCount() - 1);
    }
}
