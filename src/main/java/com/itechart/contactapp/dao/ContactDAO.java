package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Contact;

import java.util.List;
import java.util.Map;

public interface ContactDAO {
    Map<Integer, Contact> getContacts(int page);

    int getContactsCount();

    void deleteContacts(String contacts);

    void updateContact(Contact contact, int[] phoneIdForDelete);

    int createContact(Contact contact);

    List<Contact> getContactsByTodayBirthday();

    Map<Integer, Contact> searchContacts(Map<String, String> params);
}
