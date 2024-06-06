package ru.baydak.listener;

import ru.baydak.entity.Revision;
import org.hibernate.envers.RevisionListener;

public class BaydakRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        ((Revision) o).setUsername("baydak");
    }
}
