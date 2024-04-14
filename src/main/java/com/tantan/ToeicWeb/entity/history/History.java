package com.tantan.ToeicWeb.entity.history;

import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.entity.Test;
import com.tantan.ToeicWeb.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "history_part",
            joinColumns = @JoinColumn(name = "history_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id")
    )
    private List<Part> parts;
    @ManyToOne
    private User user;
    @ManyToOne
    private Test test;
    @OneToMany(mappedBy = "history")
    private List<SelectedList> selectedLists;

}
