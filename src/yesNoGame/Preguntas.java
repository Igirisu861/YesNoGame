package yesNoGame;

public class Preguntas {
    //atributos: la pregunta raíz o inicial y la actual sobre la cual está el árbol
    Pregunta preguntaRaiz;
    Pregunta preguntaActual;
    
    int numPreguntas = 0;

    //obtener pregunta actual
    public Pregunta getPreguntaActual(){
        return this.preguntaActual;
    }

    //iniciar preguntando primera pregunta
    public void startGame(){
        this.preguntaActual = this.preguntaRaiz;
    }

    //determinar si es correcta o no la respuesta
    public boolean isQuestionAnswer(){
        return !this.preguntaActual.isQuestion;
    }

    //verificar si el árbol de preguntas está lleno o no
    public boolean isEmpty(){
        return this.preguntaRaiz == null;
    }

    //preguntar la pregunta actual
    public String ask(){
        return this.preguntaActual.question;
    }

    //si es sí se mueve al yes de la pregunta actual, si el usuario dijo no, entonces 
    //retorna la pregunta no que está conectada a este nodo
    public Pregunta sigPregunta(boolean isYes){
        this.preguntaActual = isYes ? this.preguntaActual.yes : this.preguntaActual.no;
        return this.preguntaActual;
    }

    public void agregarPregunta(String pregunta, String respuesta, Boolean isYes){
        //crear la nueva pregunta y establecer sus atributos
        Pregunta newPregunta = new Pregunta();
        newPregunta.question = pregunta;
        newPregunta.isQuestion = true;
        newPregunta.anterior = this.preguntaActual.anterior;
        newPregunta.anteriorYes = this.preguntaActual.anteriorYes;

        //crear una nueva respuesta (o animal) y establecer atributos
        Pregunta newRespuesta = new Pregunta();
        newRespuesta.isQuestion = false;
        newRespuesta.question = respuesta;
        newRespuesta.anterior = newPregunta;

        /*
         * Si la pregunta es yes, entonces la nueva pregunta tiene como "yes" la nueva respuesta y la 
         * pregunta actual como respuesta "no". Igual se indica que la pregunta que iba antes de la 
         * actual es ahora 
         */
        if(isYes.booleanValue()){
            newPregunta.yes=newRespuesta;
            newPregunta.no = this.preguntaActual;
            newPregunta.anteriorYes = true;
            this.preguntaActual.anteriorYes = false;
            this.preguntaActual.anterior = newPregunta;
        }
        else{
            newPregunta.no=newRespuesta;
            newPregunta.yes = this.preguntaActual;
            newRespuesta.anteriorYes = false;
            this.preguntaActual.anteriorYes = true;
            this.preguntaActual.anterior = newPregunta;
        }

        //si la pregunta anterior era yes, entonces se establece la nueva como el nodo "yes" de la 
        //pregunta anterior. Si no es así, se pone como la pregunta "no" de la anterior
        if(newPregunta.anteriorYes.booleanValue()){
            newPregunta.anterior.yes = newPregunta;
        }
        else{
            newPregunta.anterior.no = newPregunta;
        }
    }

    //mostrar preguntas
    public void displayTree(Pregunta raiz){
        if(raiz != null){
            this.displayTree(raiz.no);
            System.out.print(" " + raiz.question);
            this.displayTree(raiz.yes);
        }
    }


    //imprimir diagrama de árbol
    public static void printBinaryTree(Pregunta raiz, int level){
        //si no hay nada, no imprime nada el método
        if(raiz == null){
            return;
        }
        //recursivamente realiza lo mismo con su rama yes
        Preguntas.printBinaryTree(raiz.yes, level+1);

        //si el nivel no es 0, entonces genera líneas según el nivel de la pregunta
        if(level!=0){
            int i=0;
            while(i<level -1){
                System.out.print("|\t");
                ++i;
            }
            System.out.println("|-------" + raiz.question);
        }
        //una vez listas las líneas, se imprime la pregunta raíz
        else{
            System.out.println(raiz.question);
        }

        //recursivamente se hace lo mismo con el lado no
        Preguntas.printBinaryTree(raiz.no, level + 1);
    }

    public void addPreguntaRaiz(String question, String yesAnswer, String noAnswer){
        Pregunta newPregunta = new Pregunta();
        newPregunta.question = question;
        newPregunta.isQuestion = true;

        Pregunta newYesRespuesta = new Pregunta();
        newYesRespuesta.isQuestion = false;
        newYesRespuesta.question = yesAnswer;
        newYesRespuesta.anterior = newPregunta;
        newYesRespuesta.anteriorYes = true;

        Pregunta newNoRespuesta = new Pregunta();
        newNoRespuesta.isQuestion = false;
        newNoRespuesta.question = noAnswer;
        newNoRespuesta.anterior = newPregunta;
        newNoRespuesta.anteriorYes = false;

        newPregunta.yes = newYesRespuesta;
        newPregunta.no = newNoRespuesta;

        this.preguntaActual = newPregunta;
        this.preguntaRaiz = newPregunta;
    }

    public void askPregunta(){
        
    }


}
