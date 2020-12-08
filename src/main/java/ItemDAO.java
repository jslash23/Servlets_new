import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ItemDAO {
   private static SessionFactory sessionFactory;

    public Item daoRead(String idn) throws NumberFormatException {
        Item item;
        long id = Long.parseLong(idn);
        try (Session session = createSessionFactory().openSession()) {

           item =  session.get(Item.class, id);
            //action
            //тут  сессия закроется автоматичесски
            //session.close();
            return item;
        }
    }

    public void daoSave(Item item) throws HibernateException {
        item.setDateCreated(new Date());
        item.setLastUpdatedDate(new Date());
        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            session.save(item);
            transaction.commit();
            System.out.println("Save Item done ");
            // throw new IOException();
        }  catch (HibernateException e) {
            System.err.println();

            e.printStackTrace();
            System.err.println("!!!!!!!" +
                    "cath worked " + "Save Item failed!!!" + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void daoUpdate(long id) throws HibernateException{
        Item item = new Item();
        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            Item findItem = findById(id);
            findItem.setName("new_book");
            findItem.setDescription("detective");
            //action
           session.update(findItem);
            //close session/tr
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Nothing update!" + e.getMessage());
        }

    }

    public void daoDelete(long idn) throws HibernateException {

        try (Session session = createSessionFactory().openSession()) {
            //
            Query query = session.createQuery("delete from  Item where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("Id", idn);
            query.executeUpdate();
            //close session/tr
            transaction.commit();
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HibernateException e) {
            System.err.println("Select from Item failed" + e.getMessage());
        }

    }

    public Item findById(Long id) {
        Item item = new Item();

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("from Item where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("Id", id);
            List list = query.list();

            //close session/tr
            transaction.commit();

            for (Object l : list) {
                item = (Item) l;
            }
            return item;
            //тут  сессия закроется автоматичесски
            //session.close();

        }
    }


    public SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
//Hear we create new sessionFactory
           sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
