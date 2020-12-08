import javax.persistence.*;
import java.util.Date;

@Entity//анотация
@Table(name = "ITEM")

public class Item  {

    private long id;//изменяю на long id меняю гетер и сетер  не работает постмен, 500 ошибка
    private String name;
    private Date dateCreated;
    private Date lastUpdatedDate;
    private String description;

    @Id
    @SequenceGenerator(name = "IT_SEQ", sequenceName = "ITEM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IT_SEQ")

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    @Column(name = "NAME")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DATE_CREATED")

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "LAST_UPDATED_DATE")

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Column(name = "DESCRIPTION")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdateDate=" + lastUpdatedDate +
                ", description='" + description + '\'' +
                '}';
    }
}
