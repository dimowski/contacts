package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Phone;

import java.util.List;

public interface PhoneDAO {
    List<Phone> getPhonesByContactId(int contactId);
}
