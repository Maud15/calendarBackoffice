package com.m2i.backoffice.dao;

import com.m2i.backoffice.model.UserCalendarRights;

import java.util.List;
import java.util.Optional;

public class UserCalendarRightsDao implements Dao<UserCalendarRights> {


    @Override
    public List<UserCalendarRights> getAll() {
        return null;
    }

    @Override
    public Optional<UserCalendarRights> get(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
