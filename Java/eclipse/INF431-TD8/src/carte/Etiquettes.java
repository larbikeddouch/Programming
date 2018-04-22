package carte;

import java.util.HashMap;

/**
 * Une <tt>HashMap</tt>, avec une valeur par défaut qui est retournée par la
 * méthode <tt>get</tt>, au lieu de <tt>null</tt> quand il n'y a pas de valeur
 * associée.
 * 
 * @param <K>
 *          type des clés
 * @param <V>
 *          type des valeurs associées
 */
@SuppressWarnings("serial")
public class Etiquettes<K, V> extends HashMap<K, V> {
  private V _defaultValue;

  /**
   * Constructeur pour spécifier la valeur par défaut.
   * 
   * @param defValue
   *          valeur par défaut
   */
  public Etiquettes(V defValue) {
    this._defaultValue = defValue;
  }

  /**
   * @param k
   * @return la valeur associée à l'objet <tt>k</tt> (cette valeur peut être
   *         <tt>null</tt>), ou la valeur par défaut quand il n'y a pas de
   *         valeur associée.
   */
  @Override
  public V get(Object k) {
    return (containsKey(k) ? super.get(k) : _defaultValue);
  }
}
