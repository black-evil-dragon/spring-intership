package com.golgan.task5.core.services;

public interface CRUDService<E, ID> extends
    CreateService<E, ID>,
    ReadService<E, ID>,
    DeleteService<E, ID> {

}
