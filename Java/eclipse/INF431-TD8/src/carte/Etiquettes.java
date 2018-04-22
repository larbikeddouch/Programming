package carte;

import java.util.HashMap;

/**
 * Une <tt>HashMap</tt>, avec une valeur par d�faut qui est retourn�e par la
 * m�thode <tt>get</tt>, au lieu de <tt>null</tt> quand il n'y a pas de valeur
 * associ�e.
 * 
 * @param <K>
 *          type des cl�s
 * @param <V>
 *          type des valeurs associ�es
 */
@SuppressWarnings("serial")
public class Etiquettes<K, V> extends HashMap<K, V> {
  private V _defaultValue;

  /**
   * Constructeur pour sp�cifier la valeur par d�faut.
   * 
   * @param defValue
   *          valeur par d�faut
   */
  public Etiquettes(V defValue) {
    this._defaultValue = defValue;
  }

  /**
   * @param k
   * @return la valeur associ�e � l'objet <tt>k</tt> (cette valeur peut �tre
   *         <tt>null</tt>), ou la valeur par d�faut quand il n'y a pas de
   *         valeur associ�e.
   */
  @Override
  public V get(Object k) {
    return (containsKey(k) ? super.get(k) : _defaultValue);
  }
}
