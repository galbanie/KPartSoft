package com.github.galbanie.models.dao.psmdb

import com.github.galbanie.utils.ParameterType
import com.github.galbanie.utils.Priority
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

/**
 * Created by Galbanie on 2017-10-09.
 */
object Sessions : IntIdTable("SESSIONS","SESSION_ID"){
    val user_id = reference("USER_ID",Users)
    val isUserProcess = bool("IS_USER_PROCESS").default(false)
}
class Session(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Session>(Sessions)
    var user by User referencedOn Sessions.user_id
    var isUserProcess by Sessions.isUserProcess
}
object HistoricSessions : IntIdTable("HISTORIC_SESSIONS"){
    val session_id = reference("SESSION_ID",Sessions)
    val dateLogin = date("DATE_LOGIN")
    val dateLogout = date("DATE_LOGOUT")
}
class HistoricSession(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<HistoricSession>(HistoricSessions)
    var session by Session referencedOn HistoricSessions.session_id
    var dateLogin by HistoricSessions.dateLogin
    var dateLogout by HistoricSessions.dateLogout
}
object Users : IntIdTable("USERS","USER_ID"){
    val username = varchar("USERNAME", 255).uniqueIndex()
    val password = varchar("PASSWORD",255)
    val passwordSalt = varchar("PASSWORD_SALT",255).nullable()
    val firstName = varchar("FIRST_NAME",255).nullable()
    val lastName = varchar("LAST_NAME",255).nullable()
    val email = varchar("EMAIL",255).nullable()
    val accountActive = bool("ACCOUNT_ACTIVE").default(false)
    val dateExpiration = date("DATE_EXPIRATION").nullable()
    val dateCreation = date("DATE_CREATION").nullable()
    val dateModified = date("DATE_MODIFIED").nullable()
}
class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)
    var username by Users.username
    var password by Users.password
    var passwordSalt by Users.passwordSalt
    var firstName by Users.firstName
    var lastName by Users.lastName
    var email by Users.email
    var accountActive by Users.accountActive
    var dateExpiration by Users.dateExpiration
    var dateCreation by Users.dateCreation
    var dateModified by Users.dateModified
}
object Roles : IntIdTable("ROLES","ROLE_ID"){
    val roleName = varchar("ROLE_NAME", 255).uniqueIndex()
    val dateCreation = date("DATE_CREATION").nullable()
    val dateModified = date("DATE_MODIFIED").nullable()
}
class Role(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Role>(Roles)
    var roleName by Roles.roleName
    var dateCreation by Roles.dateCreation
    var dateModified by Roles.dateModified
}

object UsersRoles : IntIdTable("USERS_ROLES"){
    val user_id = reference("USER_ID",Users)
    val role_id = reference("ROLE_ID",Roles)
}
class UsersRole(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UsersRole>(UsersRoles)
    var user by User referencedOn UsersRoles.user_id
    var role by Role referencedOn UsersRoles.role_id
}
object Actions : IntIdTable("ACTIONS","ACTION_ID"){
    val actionName = varchar("ACTION_NAME", 255).uniqueIndex()
    val priority = enumeration("PRIORITY", Priority::class.java)
}
class Action(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Action>(Actions)
    var actionName by Actions.actionName
    var priority by Actions.priority
}
object Parameters : IntIdTable("PARAMETERS","PARAMETER_ID"){
    val type = enumeration("TYPE", ParameterType::class.java)
    val field = varchar("FIELD",255).nullable()
    val value = varchar("VALUE",255).nullable()
}
class Parameter(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Parameter>(Parameters)
    var type by Parameters.type
    var field by Parameters.field
    var value by Parameters.value
}
/*object Entries : IntIdTable("Entries"){
    val input_id = reference("INPUT",ParameterActions)
    val constraintOutput_id = reference("CONSTRAINT_OUTPUT_ID",ConstraintOutputs)
    val valid = bool("VALID").default(false)
    val creator_id = reference("USER_ID",Users)
    val reporter_id = reference("USER_ID",Users)
    val dateCreation = date("DATE_CREATION").nullable()
    val dateModified = date("DATE_MODIFIED").nullable()
}
class Entry(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Entry>(Entries)
    var input by Parameter referencedOn Entries.input_id
    var constraintOutput by Parameter referencedOn  Entries.constraintOutput_id
    var valid by Entries.valid
    var creator by User referencedOn  Entries.creator_id
    var reporter by User referencedOn  Entries.reporter_id
    var dateCreation by Entries.dateCreation
    var dateModified by Entries.dateModified
}*/

object ParameterActions : IntIdTable("ACTIONS_PARAMETERS",""){
    val parameter_id = reference("PARAMETER_ID",Parameters)
    val action_id = reference("ACTION_ID",Actions)
}
class ParameterAction(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Parameter>(ParameterActions)
    var parameter by Parameter referencedOn  Parameters.id
    var action by Action referencedOn Actions.id
}
object Applications : IntIdTable("APPLICATIONS","APPLICATION_ID"){
    val partToBaseVehicle_id = integer("PART_BASE_VEHICLE_ID")
    val vehicleType_id = integer("VEHICLE_TYPE_ID")
    val model_id = integer("MODEL_ID")
    val subModel_id = integer("SUB_MODEL_ID")
    val mfrBodyCode_id = integer("MFR_BODY_CODE_ID")
    val bodyStyleConfig_id = integer("BODY_STYLE_CONFIG_ID")
    val bodyNumDoors_id = integer("BODY_NUM_DOORS_ID")
    val bodyType_id = integer("BODY_TYPE_ID")
    val driveType_id = integer("DRIVE_TYPE_ID")
    val engineBase_id = integer("ENGINE_BASE_ID")
    val engineDesignation_id = integer("ENGINE_DESIGNATION_ID")
    val engineVIN_id = integer("ENGINE_VIN_ID")
    val engineMfr_id = integer("ENGINE_MFR_ID")
    val fuelDeliveryConfig_id = integer("FUEL_DELIVERY_CONFIG_ID")
    val fuelDeliveryType_id = integer("FUEL_DELIVERY_TYPE_ID")
    val fuelDeliverySubType_id = integer("FUEL_DELIVERY_SUBTYPE_ID")
    val fuelSysControlType_id = integer("FUEL_SYS_CONTROL_TYPE_ID")
    val fuelSystemDesign_id = integer("FUEL_SYSTEM_DESIGN_ID")
    val aspiration_id = integer("ASPIRATION_ID")
    val cylHeadType_id = integer("CYL_HEAD_TYPE_ID")
    val fuelType_id = integer("FUEL_TYPE_ID")
    val ignitionSystemType_id = integer("IGNITION_SYSTEM_TYPE_ID")
    val transmissionType_id = integer("TRANSMISSION_TYPE_ID")
    val transmissionBase_id = integer("TRANSMISSION_BASE_ID")
    val transmissionControlType_id = integer("TRANSMISSION_CONTROL_TYPE_ID")
    val transmissionMfrCode_id = integer("TRANSMISSION_MFR_CODE_ID")
    val transmissionNumSpeeds_id = integer("TRANSMISSION_NUM_SPEEDS_ID")
    val bedLength_id = integer("BED_LENGTH_ID")
    val bedType_id = integer("BED_TYPE_ID")
    val bedConfig_id = integer("BED_CONFIG_ID")
    val wheelBase_id = integer("WHEEL_BASE_ID")
    val frontBrakeType_id = integer("FRONT_BRAKE_TYPE_ID")
    val rearBrakeType_id = integer("REAR_BRAKE_TYPE_ID")
    val frontSpringType_id = integer("FRONT_SPRING_TYPE_ID")
    val brakeSystem_id = integer("BRAKE_SYSTEM_ID")
    val brakeType_id = integer("BRAKE_TYPE_ID")
    val brakeABS_id = integer("BRAKE_ABS_ID")
    val rearSpringType_id = integer("REAR_SPRING_TYPE_ID")
    val steeringType_id = integer("STEERING_TYPE_ID")
    val steeringSystem_id = integer("STEERING_SYSTEM_ID")
    //val restraintType_id = integer("MODEL_ID")
    val region_id = integer("REGION_ID")
    val engineVersion_id = integer("ENGINE_VERSION_ID")
    val engineValves_id = integer("ENGINE_VALVES_ID")
    val note = varchar("NOTE",255)
    val dateAdded = date("DATE_ADDED")
    val dateModified = date("DATE_MODIFIED")
    val isValidable = bool("IS_VALIDABLE")
    val quantity = integer("QUANTITY")
    val upSizeTs = integer("UPSIZETS")
    val powerOutput_id = integer("POWER_OUTPUTS_ID")
    val assetLogicalName = varchar("ASSET_LOGICAL_NAME",255)
    val assetItemRef = varchar("ASSET_ITEM_REF",255)
    val assetFileName = varchar("ASSET_ITEM_REF",255)
    val transmissionElecControlled_id = integer("TRANSMISSION_ELEC_CONTROLLED_ID")
    val transmissionMFR_id = integer("TRANSMISSION_MFR_ID")
}
/*
object Applications : IntIdTable("APPLICATIONS","APPLICATION_ID"){
    val partToBaseVehicle_id = reference("PART_BASE_VEHICLE_ID",PartToBaseVehicles)
    val vehicleType_id = reference("VEHICLE_TYPE_ID",VehicleTypes)
    val model_id = reference("MODEL_ID",Models)
    val subModel_id = reference("SUB_MODEL_ID",SubModels)
    val mfrBodyCode_id = reference("MFR_BODY_CODE_ID",MfrBodyCodes)
    val bodyStyleConfig_id = reference("BODY_STYLE_CONFIG_ID",BodyStyleConfigs)
    val bodyNumDoors_id = reference("BODY_NUM_DOORS_ID",BodyNumDoors)
    val bodyType_id = reference("BODY_TYPE_ID",BodyTypes)
    val driveType_id = reference("DRIVE_TYPE_ID",DriveTypes)
    val engineBase_id = reference("ENGINE_BASE_ID",EngineBases)
    val engineDesignation_id = reference("ENGINE_DESIGNATION_ID",EngineDesignations)
    val engineVIN_id = reference("ENGINE_VIN_ID",EngineVINs)
    val engineMfr_id = reference("ENGINE_MFR_ID",EngineMfrs)
    val fuelDeliveryConfig_id = reference("FUEL_DELIVERY_CONFIG_ID",FuelDeliveryConfigs)
    val fuelDeliveryType_id = reference("FUEL_DELIVERY_TYPE_ID",FuelDeliveryTypes)
    val fuelDeliverySubType_id = reference("FUEL_DELIVERY_SUBTYPE_ID",FuelDeliverySubTypes)
    val fuelSysControlType_id = reference("FUEL_SYS_CONTROL_TYPE_ID",FuelSystemControlTypes)
    val fuelSystemDesign_id = reference("FUEL_SYSTEM_DESIGN_ID",FuelSystemDesigns)
    val aspiration_id = reference("ASPIRATION_ID",Aspirations)
    val cylHeadType_id = reference("CYL_HEAD_TYPE_ID",CylinderHeadTypes)
    val fuelType_id = reference("FUEL_TYPE_ID",FuelTypes)
    val ignitionSystemType_id = reference("IGNITION_SYSTEM_TYPE_ID",IgnitionSystemTypes)
    val transmissionType_id = reference("TRANSMISSION_TYPE_ID",TransmissionTypes)
    val transmissionBase_id = reference("TRANSMISSION_BASE_ID",TransmissionBases)
    val transmissionControlType_id = reference("TRANSMISSION_CONTROL_TYPE_ID",TransmissionControlTypes)
    val transmissionMfrCode_id = reference("TRANSMISSION_MFR_CODE_ID",TransmissionMfrCodes)
    val transmissionNumSpeeds_id = reference("TRANSMISSION_NUM_SPEEDS_ID",TransmissionNumSpeeds)
    val bedLength_id = reference("BED_LENGTH_ID",BedLengths)
    val bedType_id = reference("BED_TYPE_ID",BedTypes)
    val bedConfig_id = reference("BED_CONFIG_ID",BedConfigs)
    val wheelBase_id = reference("WHEEL_BASE_ID",WheelBases)
    val frontBrakeType_id = reference("FRONT_BRAKE_TYPE_ID",BrakeTypes)
    val rearBrakeType_id = reference("REAR_BRAKE_TYPE_ID",BrakeTypes)
    val frontSpringType_id = reference("FRONT_SPRING_TYPE_ID",SpringTypes)
    val brakeSystem_id = reference("BRAKE_SYSTEM_ID",BrakeSystems)
    val brakeType_id = reference("BRAKE_TYPE_ID",BrakeTypes)
    val brakeABS_id = reference("BRAKE_ABS_ID",Brake_Abs)
    val rearSpringType_id = reference("REAR_SPRING_TYPE_ID",SpringTypes)
    val steeringType_id = reference("STEERING_TYPE_ID",SteeringTypes)
    val steeringSystem_id = reference("STEERING_SYSTEM_ID",SteeringSystems)
    //val restraintType_id = integer("MODEL_ID")
    val region_id = reference("REGION_ID",Regions)
    val engineVersion_id = reference("ENGINE_VERSION_ID",EngineVersions)
    val engineValves_id = reference("ENGINE_VALVES_ID",Valves)
    val note = varchar("NOTE",255)
    val dateAdded = date("DATE_ADDED")
    val dateModified = date("DATE_MODIFIED")
    val isValidable = bool("IS_VALIDABLE")
    val quantity = integer("QUANTITY")
    val upSizeTs = integer("UPSIZETS")
    val powerOutput_id = reference("POWER_OUTPUTS_ID",PowerOutputs)
    val assetLogicalName = varchar("ASSET_LOGICAL_NAME",255)
    val assetItemRef = varchar("ASSET_ITEM_REF",255)
    val assetFileName = varchar("ASSET_ITEM_REF",255)
    val transmissionElecControlled_id = reference("TRANSMISSION_ELEC_CONTROLLED_ID",ElecControlleds)
    val transmissionMFR_id = reference("TRANSMISSION_MFR_ID",TransmissionMfrs)
}*/
class Application(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Application>(Applications)
    val partToBaseVehicle by Applications.partToBaseVehicle_id
    val vehicleType by  Applications.vehicleType_id
    val model by  Applications.model_id
    val subModel by Applications.subModel_id
    val mfrBodyCode by  Applications.mfrBodyCode_id
    val bodyStyleConfig by Applications.bodyStyleConfig_id
    val bodyNumDoors by Applications.bodyNumDoors_id
    val bodyType by Applications.bodyType_id
    val driveType by Applications.driveType_id
    val engineBase by Applications.engineBase_id
    val engineDesignation by Applications.engineDesignation_id
    val engineVIN by Applications.engineVIN_id
    val engineMfr by Applications.engineMfr_id
    val fuelDeliveryConfig by Applications.fuelDeliveryConfig_id
    val fuelDeliveryType by Applications.fuelDeliveryType_id
    val fuelDeliverySubType by Applications.fuelDeliverySubType_id
    val fuelSysControlType by Applications.fuelSysControlType_id
    val fuelSystemDesign by Applications.fuelSystemDesign_id
    val aspiration by Applications.aspiration_id
    val cylHeadType by Applications.cylHeadType_id
    val fuelType by Applications.fuelType_id
    val ignitionSystemType by Applications.ignitionSystemType_id
    val transmissionType by Applications.transmissionType_id
    val transmissionBase by Applications.transmissionBase_id
    val transmissionControlType by Applications.transmissionControlType_id
    val transmissionMfrCode by Applications.transmissionMfrCode_id
    val transmissionNumSpeeds by Applications.transmissionNumSpeeds_id
    val bedLength by Applications.bedLength_id
    val bedType by Applications.bedType_id
    val bedConfig by Applications.bedConfig_id
    val wheelBase by Applications.wheelBase_id
    val frontBrakeType by Applications.frontBrakeType_id
    val rearBrakeType by Applications.rearBrakeType_id
    val frontSpringType by Applications.frontSpringType_id
    val brakeSystem by Applications.brakeSystem_id
    val brakeType by Applications.brakeType_id
    val brakeABS by Applications.brakeABS_id
    val rearSpringType by Applications.rearSpringType_id
    val steeringType by Applications.steeringType_id
    val steeringSystem by Applications.steeringSystem_id
    //val restraintType_id = integer("MODEL_ID")
    val region by Applications.region_id
    val engineVersion by Applications.engineVersion_id
    val engineValves by Applications.engineValves_id
    val note by Applications.note
    val dateAdded by Applications.dateAdded
    val dateModified by Applications.dateModified
    val isValidable by Applications.isValidable
    val quantity by Applications.quantity
    val upSizeTs by Applications.upSizeTs
    val powerOutput_id by Applications.powerOutput_id
    val assetLogicalName by Applications.assetLogicalName
    val assetItemRef by Applications.assetItemRef
    val assetFileName by Applications.assetFileName
    val transmissionElecControlled_id by Applications.transmissionElecControlled_id
    val transmissionMFR_id by Applications.transmissionMFR_id
}
object PartToBaseVehicles : IntIdTable("PART_TO_BASE_VEHICLES","PART_TO_BASE_VEHICLE_ID"){
    //val baseVehicle_id = reference("BASE_VEHICLE_ID",BaseVehicles)
    //val part_id = reference("PART_ID",Parts)
    //val position_id = reference("POSITION_ID",Positions)
}
class PartToBaseVehicle(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PartToBaseVehicle>(PartToBaseVehicles)
    //var baseVehicle by BaseVehicle referencedOn  ConstraintOutputs.output_id

}