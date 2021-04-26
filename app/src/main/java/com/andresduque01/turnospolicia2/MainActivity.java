package com.andresduque01.turnospolicia2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    TextView tv01, tv02, tv03, tv04, tv05, tv06, tv07, tv08;
    RadioButton rb01, rb02, rb03, rb04, rb05, rb06;
    EditText et01;
    Button bt01;
    private  int dia, mes, anio, dias2, meses, anios;
    private  String Sevento, Sdias2, Smeses, Sanios, cero;
    private Date fechaFinal, fechaInicial, ahora;
    private SimpleDateFormat dateFormat;
    private static long back_pressed;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv01 = (TextView)findViewById(R.id.tv01);
        tv02 = (TextView)findViewById(R.id.tv02);
        tv03 = (TextView)findViewById(R.id.tv03);
        tv04 = (TextView)findViewById(R.id.tv04);
        tv05=  (TextView)findViewById(R.id.tv05);
        tv06 = (TextView)findViewById(R.id.tv06);
        tv07 = (TextView)findViewById(R.id.tv07);
        tv08 = (TextView)findViewById(R.id.tv08);
        rb01 = (RadioButton)findViewById(R.id.rb01);
        rb02 = (RadioButton)findViewById(R.id.rb02);
        rb03 = (RadioButton)findViewById(R.id.rb03);
        rb04 = (RadioButton)findViewById(R.id.rb04);
        rb05 = (RadioButton)findViewById(R.id.rb05);
        rb06 = (RadioButton)findViewById(R.id.rb06);
        et01 = (EditText)findViewById(R.id.et01);
        bt01 = (Button)findViewById(R.id.bt01);
        et01.setOnClickListener(this);


        // formatea la fecha actual y la muestra en pantalla
        ahora = new Date();


        dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        tv02.setText("La fecha actual es: "+ dateFormat.format(ahora));

        bt01.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){

                // resta las fechas final menos la actual
                fechaInicial = null;
                try {
                    fechaInicial = dateFormat.parse(dateFormat.format(ahora));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                fechaFinal = null;
                try {
                    fechaFinal = dateFormat.parse(dias2+"-"+(meses)+"-"+anios);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);

                int cont=0;
                int dias1 = dias;


                if(dias<=0){

                    Toast toast1 = Toast.makeText(getApplicationContext(), "La fecha debe ser mayor a la actual", Toast.LENGTH_SHORT);
                  //  toast1.setGravity(Gravity.CENTER,  );
                    toast1.show();
                }else {
                    tv02.setText("Faltan: " + dias + " dias para el evento");

                    //************* Calculo para los dias franco***************************

                    String vec1[] = {"franco", "disponible", "franco disponible"};

                    if ((rb01.isChecked()) && (rb04.isChecked())) {
                        String vec[] = {"tercero", "segundo y cuarto primero", "franco"};
                        int x = 0;
                        while (x < dias1) {
                            x++;
                            if (x == 3) {
                                cont++;
                                if (cont == 3) {
                                    cont = 0;
                                }
                                x = 0;
                                dias1 -= 3;
                            }
                        }

                        tv07.setText("Para la fecha su turno es: " + vec[x]);
                        tv08.setText("Para este ciclo usted se encuentra " + vec1[cont]);
                    } else if ((rb01.isChecked()) && (rb05.isChecked())) {
                        String vec[] = {"tercero", "segundo y cuarto primero", "franco"};
                        int x = 0;
                        cont++;
                        while (x < dias1) {
                            x++;
                            if (x == 3) {
                                cont++;
                                if (cont == 3) {
                                    cont = 0;
                                }
                                x = 0;
                                dias1 -= 3;
                            }
                        }

                        tv07.setText("Para la fecha su turno es: " + vec[x]);
                        tv08.setText("Para este ciclo usted se encuentra " + vec1[cont]);
                    } else if ((rb01.isChecked()) && (rb06.isChecked())) {
                        String vec[] = {"tercero", "segundo y cuarto primero", "franco"};
                        int x = 0;
                        cont += 2;
                        while (x < dias1) {
                            x++;
                            if (x == 3) {
                                cont++;
                                if (cont == 3) {
                                    cont = 0;
                                }
                                x = 0;
                                dias1 -= 3;
                            }
                        }

                        tv07.setText("Para la fecha su turno es: " + vec[x]);
                        tv08.setText("Para este ciclo usted se encuentra " + vec1[cont]);
                    }

                    //************Calculo para los dias disponible***********************
                    else if ((rb02.isChecked()) && (rb04.isChecked())) {
                        String vec[] = {"tercero", "segundo y cuarto primero", "franco"};
                        int x = 1;
                        while (x <= dias1) {
                            x++;
                            if (x == 3) {
                                cont++;
                                if (cont == 3) {
                                    cont = 0;
                                }
                                x = 0;
                                dias1 -= 3;
                            }
                        }

                        tv07.setText("Para la fecha su turno es: " + vec[x]);
                        tv08.setText("Para este ciclo usted se encuentra " + vec1[cont]);
                    } else if ((rb02.isChecked()) && (rb05.isChecked())) {
                        String vec[] = {"tercero", "segundo y cuarto primero", "franco"};
                        int x = 1;
                        cont++;
                        while (x <= dias1) {
                            x++;
                            if (x == 3) {
                                cont++;
                                if (cont == 3) {
                                    cont = 0;
                                }
                                x = 0;
                                dias1 -= 3;
                            }
                        }

                        tv07.setText("Para la fecha su turno es: " + vec[x]);
                        tv08.setText("Para este ciclo usted se encuentra " + vec1[cont]);
                    } else if ((rb02.isChecked()) && (rb06.isChecked())) {
                        String vec[] = {"tercero", "segundo y cuarto primero", "franco"};
                        int x = 1;
                        cont += 2;
                        while (x <= dias1) {
                            x++;
                            if (x == 3) {
                                cont++;
                                if (cont == 3) {
                                    cont = 0;
                                }
                                x = 0;
                                dias1 -= 3;
                            }
                        }

                        tv07.setText("Para la fecha su turno es: " + vec[x]);
                        tv08.setText("Para este ciclo usted se encuentra " + vec1[cont]);
                    }
                    //************Calculo para los dias franco disponible***********************
                    else if ((rb03.isChecked()) && (rb04.isChecked())) {
                        String vec[] = {"franco", "tercero", "segundo y cuarto primero"};
                        int x = 0;
                        while (x < dias1) {
                            x++;
                            if (x == 3) {
                                cont++;
                                if (cont == 3) {
                                    cont = 0;
                                }
                                x = 0;
                                dias1 -= 3;
                            }
                        }
                        if (x >= 1) {
                            cont++;
                            if (cont == 3) {
                                cont = 0;
                            }
                        }
                        tv07.setText("Para la fecha su turno es: " + vec[x]);
                        tv08.setText("para este ciclo usted se encuentra " + vec1[cont]);
                    } else if ((rb03.isChecked()) && (rb05.isChecked())) {
                        String vec[] = {"franco", "tercero", "segundo y cuarto primero"};
                        int x = 0;
                        cont++;
                        while (x < dias1) {
                            x++;
                            if (x == 3) {
                                cont++;
                                if (cont == 3) {
                                    cont = 0;
                                }
                                x = 0;
                                dias1 -= 3;
                            }
                        }
                        if (x >= 1) {
                            cont++;
                            if (cont == 3) {
                                cont = 0;
                            }
                        }
                        tv07.setText("Para la fecha su turno es: " + vec[x]);
                        tv08.setText("para este ciclo usted se encuentra " + vec1[cont]);
                    } else if ((rb03.isChecked()) && (rb06.isChecked())) {
                        String vec[] = {"franco", "tercero", "segundo y cuarto primero"};
                        int x = 0;
                        cont += 2;
                        while (x < dias1) {
                            x++;
                            if (x == 3) {
                                cont++;
                                if (cont == 3) {
                                    cont = 0;
                                }
                                x = 0;
                                dias1 -= 3;
                            }
                        }
                        if (x >= 1) {
                            cont++;
                            if (cont == 3) {
                                cont = 0;
                            }
                        }
                        tv07.setText("Para la fecha su turno es: " + vec[x]);
                        tv08.setText("para este ciclo usted se encuentra " + vec1[cont]);
                    }
                }
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Pulse otra vez para salir", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.condiciones) {
            terminos(null);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.compartir) {

        }else if (id == R.id.uso) {

            uso(null);
        }
        else if (id == R.id.informacion) {

            informacion(null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void terminos(View view){
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }


    public void informacion(View view){
        Intent i = new Intent(this, Main3Activity.class);
        startActivity(i);
    }

    public void uso(View view){
        Intent i = new Intent(this, Main4Activity.class);
        startActivity(i);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.et01){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthofYear, int dayofMonth) {

                    dias2 = dayofMonth;
                    Sdias2 = String.valueOf(dayofMonth) ;
                    cero = "0";
                    if(dayofMonth<10){
                        Sdias2= cero+Sdias2;
                    }

                    meses = (monthofYear+1);
                    Smeses =  String.valueOf(monthofYear+1);
                    cero = "0";
                    if(monthofYear<10){
                        Smeses= cero+Smeses;
                    }

                    anios = year;

                    Sanios =  String.valueOf(year);

                    Sevento = Sdias2+"-"+Smeses+"-"+Sanios;

                    et01.setText(Sevento);


                }
            }
                    ,anio,mes,dia);
            datePickerDialog.show();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
