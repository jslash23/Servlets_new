import org.hibernate.HibernateException;

import java.io.IOException;

public class ItemService {


    ItemDAO itemDAO = new ItemDAO();

    public Item servRead(String id) {

       return itemDAO.daoRead(id);//
    }

    public void servSave(Item item) throws HibernateException  {
        itemDAO.daoSave(item);//

    }

    public void servUpdate(long id) {

       itemDAO.daoUpdate(id);//
    }

    public void servDelete(long idn) throws HibernateException {

        itemDAO.daoDelete(idn);//
    }
}
