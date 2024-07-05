/**
 */
package fmumodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FMU</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fmumodel.FMU#getName <em>Name</em>}</li>
 *   <li>{@link fmumodel.FMU#getInput <em>Input</em>}</li>
 *   <li>{@link fmumodel.FMU#getOutput <em>Output</em>}</li>
 * </ul>
 *
 * @see fmumodel.FmumodelPackage#getFMU()
 * @model
 * @generated
 */
public interface FMU extends EObject {
   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see fmumodel.FmumodelPackage#getFMU_Name()
    * @model
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link fmumodel.FMU#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

   /**
    * Returns the value of the '<em><b>Input</b></em>' containment reference list.
    * The list contents are of type {@link fmumodel.Input}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Input</em>' containment reference list.
    * @see fmumodel.FmumodelPackage#getFMU_Input()
    * @model containment="true"
    * @generated
    */
   EList<Input> getInput();

   /**
    * Returns the value of the '<em><b>Output</b></em>' containment reference list.
    * The list contents are of type {@link fmumodel.Output}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Output</em>' containment reference list.
    * @see fmumodel.FmumodelPackage#getFMU_Output()
    * @model containment="true"
    * @generated
    */
   EList<Output> getOutput();

} // FMU
