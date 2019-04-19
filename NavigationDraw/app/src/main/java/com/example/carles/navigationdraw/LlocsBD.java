package com.example.carles.navigationdraw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class LlocsBD extends SQLiteOpenHelper {

    // Versió de la BD
    private static final int versio_BD = 1;
    // Nom de la BD
    private static final String nom_BD = "llocsBD";
    // Nom de la taula
    private static final String llocsTable = "llocs";
    // Noms de les columnes de la taula.
    private static final String llocID = "_id", llocNombre = "nombre", llocDireccion = "direccion",
    llocLongitud = "longitud", llocLatitud = "latitud", llocTipo = "tipo", llocFoto = "foto",
    llocTelefono = "telefono", llocUrl = "url", llocComentario = "comentario", llocValoracion = "valoracion";

    // Columnes
    private static final String[] COLUMNES = { llocID, llocNombre, llocDireccion, llocLongitud,
    llocLatitud, llocTipo, llocFoto, llocTelefono, llocUrl, llocComentario, llocValoracion};

    // Statement per a crear la taula "llocs"
    String createTable =
            "CREATE TABLE " + llocsTable + "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    llocNombre + " TEXT, " +
                    llocDireccion + " TEXT, " +
                    llocLongitud + " REAL, " +
                    llocLatitud + " REAL, " +
                    llocTipo + " INTEGER, " +
                    llocFoto + " TEXT, " +
                    llocTelefono + " INTEGER, " +
                    llocUrl + " TEXT, " +
                    llocComentario + " TEXT, " +
                    llocValoracion + " REAL)";

    Context context;
    public LlocsBD(Context contexto) {
        super(contexto, nom_BD, null, versio_BD);
        this.context = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        bd.execSQL(createTable);

        bd.execSQL("INSERT INTO " + llocsTable + " VALUES (null, "+
                "'Escuela Politécnica Superior de Gandía', "+
                "'C/ Paranimf, 1 46730 Gandia (SPAIN)', "+
                "-0.166093, "+
                "38.995656, "+
                TipusLloc.EDUCACION.ordinal()+
                ", '', "+
                "962849300, "+
                "'http://www.epsg.upv.es', "+
                "'Uno de los mejores lugares para formarse.', "+
                " 3.0)"
        );



        bd.execSQL("INSERT INTO " + llocsTable + " VALUES (null, " +
                "'Takumi Ramen', "+
                "'Carrer de Balmes, 59, 08007 Barcelona', "+
                "2.161568, "+
                "41.389432, "+
                TipusLloc.BAR.ordinal()+
                ", '', "+
                "636472405, "+
                "'https://www.verytastyblog.com/takumi-ramen/', "+
                "'No te pierdas el arroz con curry.', "+
                " 4.3)"
        );


        bd.execSQL("INSERT INTO " + llocsTable + " VALUES (null, "+
                "'Escola de Noves Tecnologies Interactives', "+
                "'Carrer de la Diputació, 231, 08007 Barcelona', "+
                "2.1593803, "+
                "41.3846286,"+
                TipusLloc.EDUCACION.ordinal()+
                ", '', "+
                "962849300,"+
                "'https://enti.cat/', "+
                "'Amplia tus conocimientos sobre caos y destrucción.', "+
                " 5.0)"
        );

        bd.execSQL("INSERT INTO " + llocsTable + " VALUES (null," +
                "'Barranco del Infierno',"+
                "'Vía Verde del río Serpis. Villalonga (Valencia)'," +
                " -0.295058, "+
                "38.867180, "+
                TipusLloc.NATURALEZA.ordinal()+
                ", ''," +
                " 0, "+
                "'http://sosegaos.blogspot.com.es/2009/02/lorcha-villalonga-via-verde-del-rio.html'," +
                "'Espectacular ruta para bici o andar', "+
                " 4.0)"
        );

        bd.execSQL("INSERT INTO " + llocsTable + " VALUES (null, " +
                "'La Vital', "+
                "'Avda. La Vital,0 46701 Gandia (Valencia)'," +
                "-0.1720092," +
                "38.9705949,"+
                TipusLloc.COMPRAS.ordinal() +
                ", ''," +
                "962881070, "+
                "'http://www.lavital.es'," +
                " 'El típico centro comercial', "+
                " 2.0)"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion,int newVersion) {

        bd.execSQL("DROP TABLE IF EXISTS " + llocsTable);
        this.onCreate(bd);

    }

    public void createLloc(Lloc lloc) {

        // Obtenció d'una referència de la BD per a poder escriure-hi.
        SQLiteDatabase bd = this.getWritableDatabase();

        // Creació dels valors que volem afegir.
        ContentValues values = new ContentValues();
        values.put(llocNombre, lloc.getNom());
        values.put(llocDireccion, lloc.getAddress());
        values.put(llocLongitud, lloc.getLongitud());
        values.put(llocLatitud, lloc.getLatitud());
        values.put(llocTipo, lloc.getTipus());
        values.put(llocFoto, lloc.getFoto());
        values.put(llocTelefono, lloc.getTelefon());
        values.put(llocUrl, lloc.getUrl());
        values.put(llocComentario, lloc.getComentari());
        values.put(llocValoracion, lloc.getValoracio());

        bd.insert(llocsTable, null, values);

    }

    public List getAllLlocs(){

        List llocsList = new LinkedList();

        String query = "SELECT * FROM " + llocsTable;

        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery(query, null);

        // Parse tots els resultats
        Lloc lloc = null;
        if (cursor.moveToFirst()){
            do {
               lloc = new Lloc();
               lloc.set_id(Integer.parseInt(cursor.getString(0)));
               lloc.setNom(cursor.getString(1));
               lloc.setAddress(cursor.getString(2));
               lloc.setLongitud(Double.parseDouble(cursor.getString(3)));
               lloc.setLatitud(Double.parseDouble(cursor.getString(4)));
               lloc.setTipus(Integer.parseInt(cursor.getString(5)));
               lloc.setFoto(cursor.getString(6));
               lloc.setTelefon(Integer.parseInt(cursor.getString(7)));
               lloc.setUrl(cursor.getString(8));
               lloc.setComentari(cursor.getString(9));
               lloc.setValoracio(Float.parseFloat(cursor.getString(10)));

               // Afegim el lloc a la llista de llocs
                llocsList.add(lloc);
            } while (cursor.moveToNext());
        }
        bd.close();
        return llocsList;
    }


    public int updateLloc(Lloc lloc) {

        SQLiteDatabase bd = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(llocNombre, lloc.getNom());
        values.put(llocDireccion, lloc.getAddress());
        values.put(llocLongitud, lloc.getLongitud());
        values.put(llocLatitud, lloc.getLatitud());
        values.put(llocTipo, lloc.getTipus());
        values.put(llocFoto, lloc.getFoto());
        values.put(llocTelefono, lloc.getTelefon());
        values.put(llocUrl, lloc.getUrl());
        values.put(llocComentario, lloc.getComentari());
        values.put(llocValoracion, lloc.getValoracio());

        //update
        int i = bd.update(llocsTable, values, llocID + " = ?", new String[] {String.valueOf(lloc.get_id())});

        bd.close();
        return i;
    }

    public void deleteLloc(Lloc lloc) {
        SQLiteDatabase bd = getWritableDatabase();
        bd.delete(llocsTable, llocID + " = ?", new String[] {String.valueOf(lloc.get_id())});
        bd.close();
    }


}


