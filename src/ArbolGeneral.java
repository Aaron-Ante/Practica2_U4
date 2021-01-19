/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class ArbolGeneral {
    NodoGeneral raiz;
   
    
    public ArbolGeneral(){
     raiz = null;   
    }
    
    public boolean insertarNodoGeneral(String path, char valor){
        if(path.isEmpty()){
         if(raiz == null){
           raiz = new NodoGeneral(valor);
           return true;
         }
            return false;
     }
        NodoGeneral padre = buscarNodo(path);
        if(padre == null){
            //si padre esta en null no existe padre
            return false;
        }
        NodoGeneral buscarhijo = buscarNodo(path+"/"+valor);
        //si la busqueda me regresa un null significa que no existe por tanto se crea
        //si la busqueda me regrtesa un  != de null singifica hijo ya existe 
        if(buscarhijo != null){
        return false;
    }
       
        NodoGeneral hijo = new NodoGeneral(valor);
        if(hijo == null){
            return false;
        }
        return padre.insertarLiga(hijo);
        /*
        caso 1. raiz ==null(path vacio)
        caso 2. buscar padre, si es null returna falso
        caso 3. buscar repetidos si existe return false
        caso 4. crear hijo si es null return false
        caso 5. return padre.insertarliga(hijo)
        */       
    }
    
    private NodoGeneral buscarNodo(String path){
     //considerar que el path tambien es enviado es /m   /m/w  /m/w/f.....
     if(path.isEmpty()){
         return null;
     }
     
     path = path.substring(1);
     //     m/w/a/f 
     String [] vector = path.split("/");
     if(raiz.valor == vector[0].charAt(0)){
         if(vector.length == 1){
             return raiz;
         }
         for(NodoLiga temp = raiz.ini; temp!= null; temp = temp.sig){
             if(temp.direccion.valor == vector[1].charAt(0)){
                 if(vector.length == 2){
                     return temp.direccion;
                 }
                 return buscarNodo(temp.direccion, path.substring(3));
             }
         }
     }
        return null;    
    
    }
    
    private NodoGeneral buscarNodo(NodoGeneral nodoEncontrado, String path){
        if(path.isEmpty()){
         return nodoEncontrado;
     }
        path = path.substring(1);
        String vector[];
        if(path.length()==1){
        vector = new String[1];
        vector[0] = path;
        }else{
            vector = path.split("/");
        }
        for(NodoLiga temp = nodoEncontrado.ini; temp!=null; temp = temp.sig){
            if(temp.direccion.valor == vector[0].charAt(0)){
                 
                buscarNodo(temp.direccion, path.substring(1));
             }
        }
        return null;
    }
    
    public boolean eliminarNodoGeneral(String path){
        NodoGeneral hijo = buscarNodo(path);
        
        if(hijo == null){
            return false;
        }
        if(hijo == raiz){
            if(raiz.esHoja()){
                raiz = null;
                return true;
            }
            return false;
        }
        String  pathPadre =obtenerPathPadre(path);
        NodoGeneral padre = buscarNodo(pathPadre);
        if(padre ==null){
            return false;
        }
        if(hijo.esHoja()){
            return padre.eliminarLiga(hijo);
        }
        //      /g/w/r/a buscar la ultima coincidencia de la diagonal /   /a    
        return false;
    }
    
    private String obtenerPathPadre(String pathHijo){
        int posicionUltimaDiagonal = pathHijo.lastIndexOf("/")-1;
        return pathHijo.substring(0, posicionUltimaDiagonal);
    }
    
     
     
     public String mostrarHijosRaiz(){
         if(raiz == null){
            return"ERROR: No existe raiz";
        }
         if(raiz.ini == null){
             return "ERROR: No existe nodos en raiz";
         }
          String cad="Raiz:"+raiz.valor+"\n";
          for(NodoLiga temp = raiz.ini; temp != null; temp= temp.sig){
              cad+=""+temp.direccion.valor+"\n";
          }
          return cad;
     }
     
     public String mostrarHijosNodo(String path){
         NodoGeneral padre = buscarNodo(path);
         if(padre == null){
             return "Error: No existe NodoPadre";
         }
         if(padre.ini ==null){
             return "Es hoja";
         }
         String cad="";
         for(NodoLiga temp = padre.ini; temp != null; temp = temp.sig){
         cad+=""+temp.direccion.valor+","; 
     }
         return cad;
     }
    
}
