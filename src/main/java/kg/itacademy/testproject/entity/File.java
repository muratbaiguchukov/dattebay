package kg.itacademy.testproject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "files")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class File extends BaseEntity {

    @Column(name = "file_name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false, unique = true)
    private String url;

}