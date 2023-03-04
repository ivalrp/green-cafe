package org.ival.service;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import org.ival.exception.ValidationException;
import org.ival.model.Pengguna;
import org.ival.util.PenggunaUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@ApplicationScoped
public class PenggunaService {

    Date date;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Response add(JsonObject content) throws ParseException {
        if (!Pengguna.isEmptyEmail(content.getString("email"))){
            throw new ValidationException("EMAIL_SUDAH_TERDAFTAR");
        }

        if (!PenggunaUtil.isEmail(content.getString("email"))){
            throw new ValidationException("ALAMAT_EMAIL_SALAH");
        }

        if (!PenggunaUtil.isNama(content.getString("namaLengkap"))){
            throw new ValidationException("NAMA_TIDAK_VALID");
        }

        persistPengguna(content);
        return Response.ok(new HashMap<>()).build();
    }

    @Transactional
    @TransactionConfiguration(timeout = 30)
    public Pengguna persistPengguna(JsonObject request) throws ParseException {
        Pengguna pengguna = new Pengguna();

        pengguna.setNamaLengkap(request.getString("namaLengkap"));
        pengguna.setEmail(request.getString("email"));
        pengguna.setTempatLahir(request.getString("tempatLahir"));
        pengguna.setTanggalLahir(dateFormat.parse(request.getString("tanggalLahir")));
        pengguna.setRole(request.getString("role"));

        Pengguna.persist(pengguna);
        return pengguna;

    }
}
