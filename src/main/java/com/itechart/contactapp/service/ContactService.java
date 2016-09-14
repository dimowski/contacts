package com.itechart.contactapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ContactService {
    void listContacts(HttpServletRequest request, HttpServletResponse response);
    int getContactsCount();
    void fillContact(HttpServletRequest request, HttpServletResponse response);
}
