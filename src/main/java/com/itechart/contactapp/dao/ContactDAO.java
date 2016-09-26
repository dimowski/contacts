package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Attachment;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.model.Phone;

import java.util.List;
import java.util.Map;

public interface ContactDAO {
    Map<Integer, Contact> getContacts(int page);

    int getContactsCount();

    void deleteContacts(String contacts);

    void updateContact(Contact contact, int[] phoneIdForDelete);

    void createContact(Contact contact);

    List<Contact> getContactsByTodayBirthday();

    Map<Integer, Contact> searchContacts(Map<String, String> params);
}
