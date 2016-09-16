package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Attachment;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.model.Phone;

import java.util.List;
import java.util.Map;

public interface ContactDAO {
    Map<Integer, Contact> getContacts(int page);
    int getContactsCount();
    List<Phone> getPhonesByContactId(int contactId);
    List<Attachment> getAttachmentsByContactId(int contactId);
    void deleteContacts(String contacts);
}
