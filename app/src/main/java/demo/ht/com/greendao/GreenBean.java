package demo.ht.com.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenBean {
    @Id
    private Long id;
    private String date;
    @Unique   //此字段的值唯一约束：不能重复
    private String name;
    private int age;
    @Transient
    private String type;
    @Generated(hash = 1934802655)
    public GreenBean(Long id, String date, String name, int age) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "GreenBean{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }

    @Generated(hash = 1002137420)
    public GreenBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
