/**
 */
package fmumodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fmumodel.FmumodelFactory
 * @model kind="package"
 * @generated
 */
public interface FmumodelPackage extends EPackage {
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "fmumodel";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "www.jku.at/fmumodel";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "fmumodel";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   FmumodelPackage eINSTANCE = fmumodel.impl.FmumodelPackageImpl.init();

   /**
    * The meta object id for the '{@link fmumodel.impl.FMUImpl <em>FMU</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see fmumodel.impl.FMUImpl
    * @see fmumodel.impl.FmumodelPackageImpl#getFMU()
    * @generated
    */
   int FMU = 0;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FMU__NAME = 0;

   /**
    * The feature id for the '<em><b>Input</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FMU__INPUT = 1;

   /**
    * The feature id for the '<em><b>Output</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FMU__OUTPUT = 2;

   /**
    * The number of structural features of the '<em>FMU</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FMU_FEATURE_COUNT = 3;

   /**
    * The number of operations of the '<em>FMU</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FMU_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link fmumodel.impl.InputImpl <em>Input</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see fmumodel.impl.InputImpl
    * @see fmumodel.impl.FmumodelPackageImpl#getInput()
    * @generated
    */
   int INPUT = 1;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int INPUT__NAME = 0;

   /**
    * The feature id for the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int INPUT__VALUE = 1;

   /**
    * The number of structural features of the '<em>Input</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int INPUT_FEATURE_COUNT = 2;

   /**
    * The number of operations of the '<em>Input</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int INPUT_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link fmumodel.impl.OutputImpl <em>Output</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see fmumodel.impl.OutputImpl
    * @see fmumodel.impl.FmumodelPackageImpl#getOutput()
    * @generated
    */
   int OUTPUT = 2;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int OUTPUT__NAME = 0;

   /**
    * The feature id for the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int OUTPUT__VALUE = 1;

   /**
    * The number of structural features of the '<em>Output</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int OUTPUT_FEATURE_COUNT = 2;

   /**
    * The number of operations of the '<em>Output</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int OUTPUT_OPERATION_COUNT = 0;


   /**
    * Returns the meta object for class '{@link fmumodel.FMU <em>FMU</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>FMU</em>'.
    * @see fmumodel.FMU
    * @generated
    */
   EClass getFMU();

   /**
    * Returns the meta object for the attribute '{@link fmumodel.FMU#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see fmumodel.FMU#getName()
    * @see #getFMU()
    * @generated
    */
   EAttribute getFMU_Name();

   /**
    * Returns the meta object for the containment reference list '{@link fmumodel.FMU#getInput <em>Input</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Input</em>'.
    * @see fmumodel.FMU#getInput()
    * @see #getFMU()
    * @generated
    */
   EReference getFMU_Input();

   /**
    * Returns the meta object for the containment reference list '{@link fmumodel.FMU#getOutput <em>Output</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Output</em>'.
    * @see fmumodel.FMU#getOutput()
    * @see #getFMU()
    * @generated
    */
   EReference getFMU_Output();

   /**
    * Returns the meta object for class '{@link fmumodel.Input <em>Input</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Input</em>'.
    * @see fmumodel.Input
    * @generated
    */
   EClass getInput();

   /**
    * Returns the meta object for the attribute '{@link fmumodel.Input#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see fmumodel.Input#getName()
    * @see #getInput()
    * @generated
    */
   EAttribute getInput_Name();

   /**
    * Returns the meta object for the attribute '{@link fmumodel.Input#getValue <em>Value</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Value</em>'.
    * @see fmumodel.Input#getValue()
    * @see #getInput()
    * @generated
    */
   EAttribute getInput_Value();

   /**
    * Returns the meta object for class '{@link fmumodel.Output <em>Output</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Output</em>'.
    * @see fmumodel.Output
    * @generated
    */
   EClass getOutput();

   /**
    * Returns the meta object for the attribute '{@link fmumodel.Output#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see fmumodel.Output#getName()
    * @see #getOutput()
    * @generated
    */
   EAttribute getOutput_Name();

   /**
    * Returns the meta object for the attribute '{@link fmumodel.Output#getValue <em>Value</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Value</em>'.
    * @see fmumodel.Output#getValue()
    * @see #getOutput()
    * @generated
    */
   EAttribute getOutput_Value();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   FmumodelFactory getFmumodelFactory();

   /**
    * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    *   <li>each class,</li>
    *   <li>each feature of each class,</li>
    *   <li>each operation of each class,</li>
    *   <li>each enum,</li>
    *   <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
    * @generated
    */
   interface Literals {
      /**
       * The meta object literal for the '{@link fmumodel.impl.FMUImpl <em>FMU</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see fmumodel.impl.FMUImpl
       * @see fmumodel.impl.FmumodelPackageImpl#getFMU()
       * @generated
       */
      EClass FMU = eINSTANCE.getFMU();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute FMU__NAME = eINSTANCE.getFMU_Name();

      /**
       * The meta object literal for the '<em><b>Input</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference FMU__INPUT = eINSTANCE.getFMU_Input();

      /**
       * The meta object literal for the '<em><b>Output</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference FMU__OUTPUT = eINSTANCE.getFMU_Output();

      /**
       * The meta object literal for the '{@link fmumodel.impl.InputImpl <em>Input</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see fmumodel.impl.InputImpl
       * @see fmumodel.impl.FmumodelPackageImpl#getInput()
       * @generated
       */
      EClass INPUT = eINSTANCE.getInput();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute INPUT__NAME = eINSTANCE.getInput_Name();

      /**
       * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute INPUT__VALUE = eINSTANCE.getInput_Value();

      /**
       * The meta object literal for the '{@link fmumodel.impl.OutputImpl <em>Output</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see fmumodel.impl.OutputImpl
       * @see fmumodel.impl.FmumodelPackageImpl#getOutput()
       * @generated
       */
      EClass OUTPUT = eINSTANCE.getOutput();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute OUTPUT__NAME = eINSTANCE.getOutput_Name();

      /**
       * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute OUTPUT__VALUE = eINSTANCE.getOutput_Value();

   }

} //FmumodelPackage
