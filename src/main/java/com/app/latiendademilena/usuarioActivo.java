package com.app.latiendademilena;

public class usuarioActivo {
    static String correo;
    static String usuario;
    static String latitud;
    static String longitud;

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        usuarioActivo.correo = correo;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        usuarioActivo.usuario = usuario;
    }

    public static String getLatitud() {
        return latitud;
    }

    public static void setLatitud(String latitud) {
        usuarioActivo.latitud = latitud;
    }

    public static String getLongitud() {
        return longitud;
    }

    public static void setLongitud(String longitud) {
        usuarioActivo.longitud = longitud;
    }

    public static String getTipouser() {
        return tipouser;
    }

    public static void setTipouser(String tipouser) {
        usuarioActivo.tipouser = tipouser;
    }

    static String tipouser;

}



