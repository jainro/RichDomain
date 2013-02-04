package com.rohit.domain;


import java.io.Serializable;

public interface PersistentEntity<U extends Serializable> extends  Serializable{
    U getId();
}
