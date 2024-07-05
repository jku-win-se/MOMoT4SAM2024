/**
 */
package fmumodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see fmumodel.FmumodelPackage
 * @generated
 */
public interface FmumodelFactory extends EFactory {
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   FmumodelFactory eINSTANCE = fmumodel.impl.FmumodelFactoryImpl.init();

   /**
    * Returns a new object of class '<em>FMU</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>FMU</em>'.
    * @generated
    */
   FMU createFMU();

   /**
    * Returns a new object of class '<em>Input</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Input</em>'.
    * @generated
    */
   Input createInput();

   /**
    * Returns a new object of class '<em>Output</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Output</em>'.
    * @generated
    */
   Output createOutput();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   FmumodelPackage getFmumodelPackage();

} //FmumodelFactory
