
package floyd;
public class CaminosMinimos {
    
    //Motodos para determinar todos los caminos Floyd
    
    public  String algoritmoFloyd( long [] [] mAdy) {
        int vertices = mAdy.length;
        long matrizAdyacencia [] [] = mAdy;
        
        String caminos [] [] = new String[vertices] [vertices];
        
        String caminosAuxiliares [] [] = new String[vertices][vertices];
        
        String caminRecorrido = "", cadena="", caminitos ="";
        
        int i, j, k;
        
        float temporal1,temporal2, temporal3, temporal4, minimo;
        
        //Inicializando las matrices caminos y caminosAuxiliares
        for (i=0; i<vertices; i++) {
            for (j = 0; j < vertices; j++) {
                caminos [i] [j] = "";
                caminosAuxiliares [i] [j] = "";
            }
        }
        
        // 
        
        for (k=0; k<vertices; k++) {
            for (i=0; i<vertices; i++) {
                for (j=0; j<vertices; j++) {
                    temporal1 = matrizAdyacencia [i] [j];
                    temporal2 = matrizAdyacencia [i] [k];
                    temporal3 = matrizAdyacencia [k] [j];
                    temporal4 = temporal2 + temporal3;
                    
                    // Encontramos al Minimo
                    minimo = Math.min(temporal1, temporal4);
                    
                    if(temporal1 != temporal4){
                        if (minimo == temporal4) {
                            caminRecorrido = "";
                            caminosAuxiliares [i] [j] = k + "";
                            caminos [i] [j] = caminosR( i, k, caminosAuxiliares, caminRecorrido) + (k+1);
                        }
                    }
                    matrizAdyacencia [i] [j] = (long) minimo;
                }
            }
        }
        
        // Agregando el camino minimo a cadena 
        for (i=0; i<vertices; i++) {
            for (j = 0; j < vertices; j++) {
                cadena = cadena + "["+matrizAdyacencia [i] [j]+ "]";
            }
            cadena += "\n";
        }
        
        ///////////////////////////
        for (i=0; i<vertices; i++) {
            for (j = 0; j < vertices; j++) {
                if (matrizAdyacencia [i] [j] != 1000000000){
                    if (i != j) {
                        if (caminos [i] [j].equals(""))  {
                            caminitos += "De ("+(i+1)+"--->"+(j+1)+") Irse Por... ("+(i+1)+","+(j+1)+")\n";
                        } else {
                            caminitos += "De ("+(i+1)+"--->"+(j+1)+") Irse Por..("+(i+1)+", "+caminos[i] [j]+", "+(j+1)+")\n";
                        }
                            
                    }
                } 
            }
        }
        
        
        
        return "La Matriz de Caminos Más Costos Entre Los Direfentes Vértices es:\n" +cadena+
                "\nLos Diferentes Caminos Más Cortos Entre Vértices son: \n "+caminitos;
    }
    
    public String caminosR( int i, int k, String [] [] caminosAuxiliares ,String caminRecorrido){
        if(caminosAuxiliares[i][k].equals("")){
            return "";
        }else {
            caminRecorrido += caminosR(i, Integer.parseInt(caminosAuxiliares[i][k].toString()), caminosAuxiliares, caminRecorrido) + 
                        (Integer.parseInt(caminosAuxiliares[i][k].toString())+1+", ");
            return caminRecorrido;
        }
    }
}
