/**
 */
package fmumodel.impl;

import fmumodel.FMU;
import fmumodel.FmumodelPackage;
import fmumodel.Input;
import fmumodel.Output;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>FMU</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fmumodel.impl.FMUImpl#getName <em>Name</em>}</li>
 *   <li>{@link fmumodel.impl.FMUImpl#getInput <em>Input</em>}</li>
 *   <li>{@link fmumodel.impl.FMUImpl#getOutput <em>Output</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FMUImpl extends MinimalEObjectImpl.Container implements FMU {
   /**
    * The default value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected static final String NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected String name = NAME_EDEFAULT;

   /**
    * The cached value of the '{@link #getInput() <em>Input</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInput()
    * @generated
    * @ordered
    */
   protected EList<Input> input;

   /**
    * The cached value of the '{@link #getOutput() <em>Output</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOutput()
    * @generated
    * @ordered
    */
   protected EList<Output> output;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected FMUImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return FmumodelPackage.Literals.FMU;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String getName() {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void setName(String newName) {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, FmumodelPackage.FMU__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EList<Input> getInput() {
      if (input == null) {
         input = new EObjectContainmentEList<Input>(Input.class, this, FmumodelPackage.FMU__INPUT);
      }
      return input;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EList<Output> getOutput() {
      if (output == null) {
         output = new EObjectContainmentEList<Output>(Output.class, this, FmumodelPackage.FMU__OUTPUT);
      }
      return output;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
      switch (featureID) {
         case FmumodelPackage.FMU__INPUT:
            return ((InternalEList<?>)getInput()).basicRemove(otherEnd, msgs);
         case FmumodelPackage.FMU__OUTPUT:
            return ((InternalEList<?>)getOutput()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case FmumodelPackage.FMU__NAME:
            return getName();
         case FmumodelPackage.FMU__INPUT:
            return getInput();
         case FmumodelPackage.FMU__OUTPUT:
            return getOutput();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID) {
         case FmumodelPackage.FMU__NAME:
            setName((String)newValue);
            return;
         case FmumodelPackage.FMU__INPUT:
            getInput().clear();
            getInput().addAll((Collection<? extends Input>)newValue);
            return;
         case FmumodelPackage.FMU__OUTPUT:
            getOutput().clear();
            getOutput().addAll((Collection<? extends Output>)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID) {
      switch (featureID) {
         case FmumodelPackage.FMU__NAME:
            setName(NAME_EDEFAULT);
            return;
         case FmumodelPackage.FMU__INPUT:
            getInput().clear();
            return;
         case FmumodelPackage.FMU__OUTPUT:
            getOutput().clear();
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID) {
      switch (featureID) {
         case FmumodelPackage.FMU__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case FmumodelPackage.FMU__INPUT:
            return input != null && !input.isEmpty();
         case FmumodelPackage.FMU__OUTPUT:
            return output != null && !output.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString() {
      if (eIsProxy()) return super.toString();

      StringBuilder result = new StringBuilder(super.toString());
      result.append(" (name: ");
      result.append(name);
      result.append(')');
      return result.toString();
   }

} //FMUImpl
