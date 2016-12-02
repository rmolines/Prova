package mvc.model;


import java.util.Calendar;


public class Jogador {
    private Long id;
    private String usuario;
    private boolean finalizado;
    private Calendar dataFinalizacao;


    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public boolean isFinalizado() {return finalizado;}
    public void setFinalizado(boolean finalizado) {this.finalizado = finalizado;}
    public Calendar getDataFinalizacao() {return dataFinalizacao;}
    public void setDataFinalizacao(Calendar dataFinal) {this.dataFinalizacao = dataFinal;}
}