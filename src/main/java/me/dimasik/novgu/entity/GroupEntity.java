package me.dimasik.novgu.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "group")
@Table(name = "groups")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String groupName;
    private Long ownerId;

    @ElementCollection(targetClass = FileEntity.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "good_subjects", joinColumns = @JoinColumn(name = "group_id"))
    private Set<FileEntity> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getOwner() {
        return ownerId;
    }

    public void setOwner(Long owner) {
        this.ownerId = owner;
    }

    public Set<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(Set<FileEntity> files) {
        this.files = files;
    }
}
