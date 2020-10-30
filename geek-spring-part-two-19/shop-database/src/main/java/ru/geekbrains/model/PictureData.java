package ru.geekbrains.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pictures_data")
public class PictureData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Type(type="org.hibernate.type.BinaryType") // для правильной работы PostgreSQL
    @Column(name = "data", nullable = false, length = 33554430) // для правильной hibernate-валидации в MySQL
    private byte[] data;

    public PictureData(byte[] data) {
        this.data = data;
    }
}
