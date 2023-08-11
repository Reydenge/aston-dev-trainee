package ru.reydenge.simpleservlet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Elective {
    private int id;
    private String title;

    public Elective(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elective elective = (Elective) o;
        return getId() == elective.getId() && Objects.equals(getTitle(), elective.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }

    @Override
    public String toString() {
        return "Elective{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
