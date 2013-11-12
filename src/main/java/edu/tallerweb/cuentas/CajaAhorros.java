package edu.tallerweb.cuentas;

/**
 * Similar a la CuentaSueldo, pero se pide que luego de la
 * quinta extracción de dinero se cobre un costo adicional
 * por extracción de $ 6
 */
public class CajaAhorros extends AbstractCuenta{
	private int extracciones;
	
	/**
	 * No hay reglas adicionales para el depósito
	 * @param monto a depositar
	 */
	public void depositar(final Double monto)throws RuntimeException {
		
		if(monto < 0.0)
			throw new CuentaBancariaException("monto depositado negativo");
		
		this.monto = this.monto + monto;
	}

	/**
	 * Se cobran $6 adicionales por cada extracción luego de
	 * la quinta.
	 * @param monto a extraer
	 */
	public void extraer(final Double monto)throws RuntimeException {
		Double adicional = 6.0; 	
		if(this.extracciones < 5){
			if(this.monto - monto < 0.0)
				throw new CuentaBancariaException("monto insuficiente");
			else{
				this.monto = this.monto - monto;
				this.extracciones++;
			}	
		}else{
			if(this.monto - monto - adicional < 0.0)
				throw new CuentaBancariaException("monto insuficiente");
			else
				this.monto = this.monto - monto - adicional;
		}	
	}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return this.monto;
	}
	
	public void setExtracciones(int ext){
		this.extracciones = ext;
	}
	
	
	
	
}
