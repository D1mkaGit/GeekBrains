package ru.geekbrains.persist.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
