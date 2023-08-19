import java.io.*;

public class carSales {

    public static void main(String[] args) {

        //con estos objetos escribiremos en nuestro archivo "file" en el que escribiremos nuestra propio csv file
        File file = new File("tabla_ventas.csv");//archivo que contendra datos sumados (creado por nosotros)
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;//escritores para nuestro archivo
        try{//terminamos de crear los objetos que escribiran nuestro archivo final
        fileWriter = new FileWriter(file);
        bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {throw new RuntimeException(e);}

        double[] arrayVentas = new double[1000];//contendrá las ganancias (precios) de cada venta registrada
        FileReader fileReader = null;//lector de el archivo otorgado hacia nosotros
        try{fileReader = new FileReader("car_sales.txt");//de paso el que leerá el archivo que se nos dió
        }catch (IOException e){System.out.println("archivo faltante");}
        BufferedReader br = new BufferedReader(fileReader);//usamos el buffered reader para aprovechar sus facilidades


        //variables auxiliares
        String capturadorDeLineas = "";
        double sumadorDePrecios = 0;

        try{br.readLine();//para saltarme la primera linea de datos (skipping the header)
        }catch (Exception e){System.out.println("no va a pasar nada, pero para que no moleste el intelij");}


        for (int i = 0; i < 1000; i++) {//for que recorrerá los renglones del archivo que nos dieron
            try{

                capturadorDeLineas = br.readLine();//guardamos línea leida en capturador
                int indice = (capturadorDeLineas.indexOf('$')+1);//guardamos en donde comienza el valor -despues de '$'
                capturadorDeLineas = capturadorDeLineas.substring(indice);//recortamos el renglon hasta donde nos importa
                double valor = Double.parseDouble(capturadorDeLineas);//ese numero lo guardamos en valor
                //usaremos valor para rellenar el arreglo de ventas
                //recordemos que ese arreglo guardará todas las ventas para hacer la suma prefija de ventas
                arrayVentas[i] = valor;//llenamos el arreglo en su totalidad usando valor
                sumadorDePrecios = sumadorDePrecios + arrayVentas[i];//hacemos la suma PREFIJA con el sumador de precios
                System.out.println(sumadorDePrecios);//imprimimos para que se vean que están bien las sumas



                //////////////////////////////////////////////
                //Escribiremos los datos en el .csv nuevo nuestro aquí "tabla_ventas.csv" se llama el nuestro

                bufferedWriter.write(sumadorDePrecios+","+"\n");//ponemos la suma prefija, seguido de la coma
                //y seguido de un salto de linea para su legibilidad

                //////////////////////////////////////////////

            }catch (Exception e){}
        }//final del for

            try{
            bufferedWriter.flush();//flusheamos para que si se escriba en el archivo lo que escribimos con el ".write"
            bufferedWriter.close();//CERRAMOS el escritor para que no estorbe con otras apps o quien sea que lo utilice
            }catch (Exception e){}

        System.out.println("Total de suma de ventas: "+sumadorDePrecios);
            //imprimimos las ventas totales aparte para que el usuario corrobore el dato


    }//fin main
}//fin clase
