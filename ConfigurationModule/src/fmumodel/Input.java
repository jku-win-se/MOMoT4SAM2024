/**
 */
package fmumodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fmumodel.Input#getName <em>Name</em>}</li>
 *   <li>{@link fmumodel.Input#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see fmumodel.FmumodelPackage#getInput()
 * @model
 * @generated
 */
public interface Input extends EObject {
   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see fmumodel.FmumodelPackage#getInput_Name()
    * @model
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link fmumodel.Input#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

   /**
    * Returns the value of the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Value</em>' attribute.
    * @see #setValue(double)
    * @see fmumodel.FmumodelPackage#getInput_Value()
    * @model
    * @generated
    */
   double getValue();

   /**
    * Sets the value of the '{@link fmumodel.Input#getValue <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Value</em>' attribute.
    * @see #getValue()
    * @generated
    */
   void setValue(double value);

} // Input
