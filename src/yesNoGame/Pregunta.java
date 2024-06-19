package yesNoGame;
public class Pregunta{
    int key;

    //el contenido de la pregunta (o valor)
    String question;

    //si es una pregunta o no
    public boolean isQuestion = true;

    //"nodo" padre
    Pregunta anterior;

    //si la pregunta anterior era sí o no
    Boolean anteriorYes;

    //nodos izquierdo y derecho
    Pregunta no;
    Pregunta yes;
}