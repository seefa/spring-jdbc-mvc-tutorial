package ir.seefa.tutorial.spring.repository;

import ir.seefa.tutorial.spring.entity.Contact;

import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 10 Sep 2020 T 00:06:31
 */
public interface ContactDao {

    List<Contact> findAll();

    Contact findById(int contactId);

    List<Contact> findByNameAndFamily(String name, String family);

    List<Contact> findByPhone(String phone);

    List<Contact> findByPhoneWithNamedParameters(String phone);

    boolean addOrUpdateContact(Contact contact);

    boolean delete(int contactId);

    int addContactWithSimpleJdbcInsert(Contact contact);

    int addContactWithSimpleJdbcInsertReturnNewContactId(Contact contact);

}
