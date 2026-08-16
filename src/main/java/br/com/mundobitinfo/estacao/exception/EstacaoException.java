package br.com.mundobitinfo.estacao.exception;

public class EstacaoException   extends RuntimeException{
   
    public EstacaoException(String msgUsuario) {
		// TODO Auto-generated constructor stub
		this.msgUsuario = msgUsuario;
	}
	public EstacaoException(String msgUsuario,String msgPadrao) {
		// TODO Auto-generated constructor stub
		this.msgUsuario = msgUsuario;
		this.msgPadrao = msgPadrao;
	}
	public EstacaoException() {
		// TODO Auto-generated constructor stub
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msgUsuario;
	private String msgPadrao;
	public String getMsgPadrao() {
		return msgPadrao;
	}
	public void setMsgPadrao(String msgPadrao) {
		this.msgPadrao = msgPadrao;
	}
	public String getMsgUsuario() {
		return msgUsuario;
	}
	public void setMsgUsuario(String msgUsuario) {
		this.msgUsuario = msgUsuario;
	}

}
