package exception;

/**
 * Custom exception class to handle price not found situation
 * 
 * @author vrajesh
 *
 */
public class PriceNotFoundException extends Exception {

	private static final long serialVersionUID = -8504069333211571394L;

	public PriceNotFoundException(Long courseId, Long countryId) {
		super("Price not found for courseId:" + courseId + " and countryId:" + countryId);
	}
}
