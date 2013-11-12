package edu.tallerweb.cuentas;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class CuentaTests {

	@Test
	public void queVerifiqueLaConsigna() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(500.0);
	
		Assert.assertEquals(
				"al extraer $ 500.0 de una cuenta con $ 4000.0 se obtienen $ 3500.0",
				3500.0, cuenta.getSaldo(), 0.0);
	}

	@Test(expected=CuentaBancariaException.class)
	public void queVerifiqueLaConsignaException() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(3500.0);

		cuenta.extraer(4000.0);
	}
	
	@Test(expected = CuentaBancariaException.class )
	public void verificarSaldoNegativoException(){
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(-5000.0);
	}
	
	@Test 
	public void verificarCajaDeAhorro() {
		CajaAhorros caja = new CajaAhorros();
		caja.depositar(2000.0);
		
		Assert.assertEquals("verifico que el saldo se halla depositado correctamente"
				,2000.0, caja.getSaldo(),0.0);
		caja.setExtracciones(0);
		
		caja.extraer(1500.0);
		
		Assert.assertEquals("verifico que al extraer 1500 queden 500"
				,500.0, caja.getSaldo(),0.0);
	
		caja.extraer(50.0);
		caja.extraer(50.0);
		caja.extraer(50.0);
		caja.extraer(50.0);
		caja.extraer(50.0);
		
		
		Assert.assertEquals("verifico que a partir de la 5ta extraccion"
		+"se cobra un costo adional de $6 por extraccion",244.0, caja.getSaldo(),0.0);
		
		}
	
		@Test(expected = CuentaBancariaException.class )
		public void verificarCajaAhorroException(){
			CajaAhorros caja1 = new CajaAhorros();
			caja1.depositar(-4000.0);
		}
		
		@Test(expected = CuentaBancariaException.class )
		public void CajaAhorroExtraccionException(){
			CajaAhorros caja1 = new CajaAhorros();
			caja1.depositar(4000.0);
		
			caja1.extraer(5000.0);
		}
		@Test(expected = CuentaBancariaException.class )
		public void CajaCostoExtraccionException(){
			CajaAhorros caja1 = new CajaAhorros();
			caja1.depositar(1000.0);
			caja1.setExtracciones(0);
			caja1.extraer(200.0);
			caja1.extraer(200.0);
			caja1.extraer(200.0);
			caja1.extraer(200.0);
			caja1.extraer(100.0);
			caja1.extraer(100.0);
		}
		
		@Test
		public void verificarCuentaCorriente(){
			CuentaCorriente cuenta = new CuentaCorriente(100.0);
			cuenta.depositar(100.0);
			assertEquals(100.0, cuenta.getSaldo(),0.0);
			assertEquals(0.0,cuenta.getDescubierto(),0.0);
			
			cuenta.extraer(150.0);
			assertEquals(52.5,cuenta.getDescubierto(),0.0);
			
			cuenta.depositar(100.0);
			assertEquals(47.5,cuenta.getSaldo(),0.0);
			assertEquals(0.0,cuenta.getDescubierto(),0.0);
		}
		
		@Test(expected = CuentaBancariaException.class)
		public void CuentaCorrienteDepositoException(){
			CuentaCorriente cuenta = new CuentaCorriente(100.0);
			cuenta.depositar(-100.0);
		}
		
		@Test(expected = CuentaBancariaException.class)
		public void CuentaCorrienteExtractionException(){
			CuentaCorriente cuenta = new CuentaCorriente(100.0);
			cuenta.depositar(100.0);
			cuenta.extraer(350.0);
		}
}
