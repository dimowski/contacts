package com.itechart.contactapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ContactService {
    int getContactsCount();

    void createContact(HttpServletRequest request, HttpServletResponse response);

    void deleteContact(HttpServletRequest request, HttpServletResponse response);

    void saveContact(HttpServletRequest request, HttpServletResponse response);

    void showContactsList(HttpServletRequest request, HttpServletResponse response);

    void editContact(HttpServletRequest request, HttpServletResponse response);

    void addAttachment(HttpServletRequest request, HttpServletResponse response);

    void removeAttachment(HttpServletRequest request, HttpServletResponse response);
}