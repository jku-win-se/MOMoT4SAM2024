/**
 */
package fmumodel.impl;

import fmumodel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FmumodelFactoryImpl extends EFactoryImpl implements FmumodelFactory {
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static FmumodelFactory init() {
      try {
         FmumodelFactory theFmumodelFactory = (FmumodelFactory)EPackage.Registry.INSTANCE.getEFactory(FmumodelPackage.eNS_URI);
         if (theFmumodelFactory != null) {
            return theFmumodelFactory;
         }
      }
      catch (Exception exception) {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new FmumodelFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FmumodelFactoryImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EObject create(EClass eClass) {
      switch (eClass.getClassifierID()) {
         case FmumodelPackage.FMU: return createFMU();
         case FmumodelPackage.INPUT: return createInput();
         case FmumodelPackage.OUTPUT: return createOutput();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public FMU createFMU() {
      FMUImpl fmu = new FMUImpl();
      return fmu;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Input createInput() {
      InputImpl input = new InputImpl();
      return input;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Output createOutput() {
      OutputImpl output = new OutputImpl();
      return output;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public FmumodelPackage getFmumodelPackage() {
      return (FmumodelPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static FmumodelPackage getPackage() {
      return FmumodelPackage.eINSTANCE;
   }

} //FmumodelFactoryImpl
