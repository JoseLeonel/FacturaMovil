package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Canton;
import com.emprendesoftcr.modelo.Distrito;
import com.emprendesoftcr.modelo.Provincia;

public interface ProvinciaBo {

	Provincia findByCodigo(Integer codigo);
	
	Canton findCantonByCodigo(Integer codigoProvincia, Integer codigoCanton);

	Distrito findDistritoByCodigo(Integer codigoProvincia, Integer codigoCanton, Integer codigoDistrito);
	
}