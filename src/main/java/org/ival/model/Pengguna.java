package org.ival.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pengguna")
public class Pengguna extends PanacheEntityBase {

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", nullable = false,length = 36)
    private String id;

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "email",length = 50,nullable = false)
    private String email;

    @Column(name = "tempat_lahir",length = 20,nullable = false)
    private String tempatLahir;

    public Pengguna() {
        super();
    }

    @Column(name = "tanggal_lahir",nullable = false)
    private Date tanggalLahir;

    @Column(name = "role",length = 20, nullable = false)
    private String role;

    public static Boolean isEmptyEmail(String email){
        return Pengguna.find("email = ?1", email).firstResultOptional().isEmpty();
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
