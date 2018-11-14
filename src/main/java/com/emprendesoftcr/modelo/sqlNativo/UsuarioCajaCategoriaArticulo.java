package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Modelo para totalizar los articulos de una caja
 */
@BaseNativeQuery(name = "totalesCategoriaArticuloCaja", 
query = "SELECT cate.descripcion as categoria, art.descripcion as articulo, sum(deta.cantidad) as total, sum(deta.monto_total_linea) as total_categoria FROM detalles deta"
	+ " left join articulos art on art.codigo = deta.codigo and art.empresa_id = :ID_EMPRESA"
	+ " left join categorias cate on cate.id = art.categoria_id"
	+ " where deta.codigo !='8888' and  deta.factura_id in ("
		+ " SELECT fact.id FROM usuarios_cajas caja "
			+ "	left join usuarios_cajas_facturas factusua on factusua.usuario_caja_id = caja.id "
			+ " left join facturas fact on fact.id = factusua.factura_id, detalles deta"
			+ " where fact.ref_codigo !='01' and fact.estado = 2 and (fact.total_credito = 0 or fact.total_credito is null) and caja.id = :ID_CAJA_USUARIO group by id "
		+ " ) group by categoria, articulo")
@Entity
public class UsuarioCajaCategoriaArticulo implements Serializable {

	private static final long serialVersionUID = 8895530294398977996L;

	@Id
	@Column(name = "categoria")
	private String categoria;

	@Id
	@Column(name = "articulo")
	private String articulo;

	@Id
	@Column(name = "total")
	private Integer total;

	@Id
	@Column(name = "total_categoria")
	private Double totalCategoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Double getTotalCategoria() {
		return totalCategoria;
	}

	public void setTotalCategoria(Double totalCategoria) {
		this.totalCategoria = totalCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articulo == null) ? 0 : articulo.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioCajaCategoriaArticulo other = (UsuarioCajaCategoriaArticulo) obj;
		if (articulo == null) {
			if (other.articulo != null)
				return false;
		} else if (!articulo.equals(other.articulo))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}
	
	
}

