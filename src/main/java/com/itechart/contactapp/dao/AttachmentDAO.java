package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Attachment;

import java.util.List;

public interface AttachmentDAO {

    void createAttachments(List<Attachment> attachments);

    String removeAttachment(int id);

    void updateAttachments(List<Attachment> attachments);

    List<Attachment> getAttachmentsByContactId(int contactId);

}
