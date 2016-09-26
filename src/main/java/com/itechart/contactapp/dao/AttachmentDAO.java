package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Attachment;

import java.util.List;

public interface AttachmentDAO {

    void createAttachment(Attachment attachment);

    void removeAttachment(int contactId, String fileName);

    List<Attachment> getAttachmentsByContactId(int contactId);

}
