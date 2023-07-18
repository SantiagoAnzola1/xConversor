public enum ConversorUnidadesLongitud {
	
	KILOMETRO("Kilómetro", "km", 1E-3),
    METRO("Metro", "m", 1),
    DECIMETRO("Decímetro", "dm", 10),
    CENTIMETRO("Centímetro", "cm", 100),
    MILIMETRO("Milímetro", "mm", 1000),
    MICROMETRO("Micrómetro", "µm", 1000000),
    NANOMETRO("Nanómetro", "nm", 1000000000),
	MILLA("Milla", "mi", 6.21371e-4),
	YARDA("Yarda", "yd", 1.09361),
	PIE("Pie", "ft", 3.28084),
	PULGADA("Pulgada", "in", 39.3701),
	MILLANAUTICA("Milla náutica", "Milla náutica", 5.39957e-4);

	private final String nombre;
	private final String abreviacion;
	private final double factor;

	ConversorUnidadesLongitud(String name, String abbreviation, double conversionFactor) {
		this.nombre = name;
		this.abreviacion = abbreviation;
		this.factor = conversionFactor;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAbreviacion() {
		return abreviacion;
	}

	public double getFactor() {
		return factor;
	}

	public static ConversorUnidadesLongitud getUnidadBynombre(String nombre) {
		for (ConversorUnidadesLongitud unit : ConversorUnidadesLongitud.values()) {
			if (unit.getNombre().equals(nombre)) {
				return unit;
			}
		}
		return null;
	}

	public double getConversionTo(ConversorUnidadesLongitud unidadObjetivo, double cantidad) {

		return (cantidad * unidadObjetivo.getFactor()) / this.factor;
	}
}
