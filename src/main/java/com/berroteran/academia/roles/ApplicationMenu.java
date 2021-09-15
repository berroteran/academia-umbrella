package com.berroteran.academia.roles;
//        Defina los menu Bar mediante menuBarXXXX, y los elementos con su nombre respectivo.
//        Nota: Genere los métodos set/get en el ejemplo se omiten para ahorrar lineas.

import com.avbravo.avbravoutils.JsfUtil.menu.MenuElement;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @authoravbravo
 */
@Named
@SessionScoped
public class ApplicationMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    public ApplicationMenu() {
    }
    /*
     barra de menu
     */
    private Boolean menuBarAsistencia = false;
    private Boolean menuBarAdministracion = false;

    private Boolean menuBarInventario = false;
    private Boolean menuBarFacturacion = false;
    private Boolean menuBarPlanilla = false;

    private Boolean menuBarContabilidad = false;
    private Boolean menuBarOrdenes = false;
    private Boolean menuBarRegistros = false;
    private Boolean menuBarReportes = false;

    /*
     elementos
     */
    MenuElement articulo = new MenuElement();
    MenuElement banco = new MenuElement();
    MenuElement bodega = new MenuElement();
    MenuElement clientes = new MenuElement();
    MenuElement color = new MenuElement();
    MenuElement conyuge = new MenuElement();
    MenuElement corregimiento = new MenuElement();
    MenuElement distrito = new MenuElement();
    MenuElement ganga = new MenuElement();
    MenuElement grupoarticulo = new MenuElement();
    MenuElement interno = new MenuElement();
    MenuElement modelo = new MenuElement();
    MenuElement orden = new MenuElement();
    MenuElement porcentaje = new MenuElement();
    MenuElement provincia = new MenuElement();
    MenuElement proveedor = new MenuElement();
    MenuElement rol = new MenuElement();
    MenuElement tamano = new MenuElement();
    MenuElement tienda = new MenuElement();
    MenuElement tipoorden = new MenuElement();
    MenuElement subtipoorden = new MenuElement();

    MenuElement usuario = new MenuElement();

    public void enabledAll(Boolean activo) {
        menuBarRegistros = activo;
        menuBarInventario = activo;
        menuBarFacturacion = activo;
        menuBarPlanilla = activo;
        menuBarAsistencia = activo;
        menuBarContabilidad = activo;
        menuBarAdministracion = activo;
        menuBarOrdenes = activo;
        menuBarReportes = activo;

        articulo.initialize(activo);
        banco.initialize(activo);
        bodega.initialize(activo);
        color.initialize(activo);
        clientes.initialize(activo);
        conyuge.initialize(activo);
        corregimiento.initialize(activo);
        distrito.initialize(activo);
        ganga.initialize(activo);
        grupoarticulo.initialize(activo);
        interno.initialize(activo);
        modelo.initialize(activo);
        orden.initialize(activo);

        porcentaje.initialize(activo);
        proveedor.initialize(activo);
        provincia.initialize(activo);
        rol.initialize(activo);
        tamano.initialize(activo);
        tienda.initialize(activo);
        tipoorden.initialize(activo);
        subtipoorden.initialize(activo);

        usuario.initialize(activo);

    }

    // Coloque los get/set


    public Boolean getMenuBarAsistencia() {
        return menuBarAsistencia;
    }

    public void setMenuBarAsistencia(Boolean menuBarAsistencia) {
        this.menuBarAsistencia = menuBarAsistencia;
    }

    public Boolean getMenuBarAdministracion() {
        return menuBarAdministracion;
    }

    public void setMenuBarAdministracion(Boolean menuBarAdministracion) {
        this.menuBarAdministracion = menuBarAdministracion;
    }

    public Boolean getMenuBarInventario() {
        return menuBarInventario;
    }

    public void setMenuBarInventario(Boolean menuBarInventario) {
        this.menuBarInventario = menuBarInventario;
    }

    public Boolean getMenuBarFacturacion() {
        return menuBarFacturacion;
    }

    public void setMenuBarFacturacion(Boolean menuBarFacturacion) {
        this.menuBarFacturacion = menuBarFacturacion;
    }

    public Boolean getMenuBarPlanilla() {
        return menuBarPlanilla;
    }

    public void setMenuBarPlanilla(Boolean menuBarPlanilla) {
        this.menuBarPlanilla = menuBarPlanilla;
    }

    public Boolean getMenuBarContabilidad() {
        return menuBarContabilidad;
    }

    public void setMenuBarContabilidad(Boolean menuBarContabilidad) {
        this.menuBarContabilidad = menuBarContabilidad;
    }

    public Boolean getMenuBarOrdenes() {
        return menuBarOrdenes;
    }

    public void setMenuBarOrdenes(Boolean menuBarOrdenes) {
        this.menuBarOrdenes = menuBarOrdenes;
    }

    public Boolean getMenuBarRegistros() {
        return menuBarRegistros;
    }

    public void setMenuBarRegistros(Boolean menuBarRegistros) {
        this.menuBarRegistros = menuBarRegistros;
    }

    public Boolean getMenuBarReportes() {
        return menuBarReportes;
    }

    public void setMenuBarReportes(Boolean menuBarReportes) {
        this.menuBarReportes = menuBarReportes;
    }

    public MenuElement getArticulo() {
        return articulo;
    }

    public void setArticulo(MenuElement articulo) {
        this.articulo = articulo;
    }

    public MenuElement getBanco() {
        return banco;
    }

    public void setBanco(MenuElement banco) {
        this.banco = banco;
    }

    public MenuElement getBodega() {
        return bodega;
    }

    public void setBodega(MenuElement bodega) {
        this.bodega = bodega;
    }

    public MenuElement getClientes() {
        return clientes;
    }

    public void setClientes(MenuElement clientes) {
        this.clientes = clientes;
    }

    public MenuElement getColor() {
        return color;
    }

    public void setColor(MenuElement color) {
        this.color = color;
    }

    public MenuElement getConyuge() {
        return conyuge;
    }

    public void setConyuge(MenuElement conyuge) {
        this.conyuge = conyuge;
    }

    public MenuElement getCorregimiento() {
        return corregimiento;
    }

    public void setCorregimiento(MenuElement corregimiento) {
        this.corregimiento = corregimiento;
    }

    public MenuElement getDistrito() {
        return distrito;
    }

    public void setDistrito(MenuElement distrito) {
        this.distrito = distrito;
    }

    public MenuElement getGanga() {
        return ganga;
    }

    public void setGanga(MenuElement ganga) {
        this.ganga = ganga;
    }

    public MenuElement getGrupoarticulo() {
        return grupoarticulo;
    }

    public void setGrupoarticulo(MenuElement grupoarticulo) {
        this.grupoarticulo = grupoarticulo;
    }

    public MenuElement getInterno() {
        return interno;
    }

    public void setInterno(MenuElement interno) {
        this.interno = interno;
    }

    public MenuElement getModelo() {
        return modelo;
    }

    public void setModelo(MenuElement modelo) {
        this.modelo = modelo;
    }

    public MenuElement getOrden() {
        return orden;
    }

    public void setOrden(MenuElement orden) {
        this.orden = orden;
    }

    public MenuElement getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(MenuElement porcentaje) {
        this.porcentaje = porcentaje;
    }

    public MenuElement getProvincia() {
        return provincia;
    }

    public void setProvincia(MenuElement provincia) {
        this.provincia = provincia;
    }

    public MenuElement getProveedor() {
        return proveedor;
    }

    public void setProveedor(MenuElement proveedor) {
        this.proveedor = proveedor;
    }

    public MenuElement getRol() {
        return rol;
    }

    public void setRol(MenuElement rol) {
        this.rol = rol;
    }

    public MenuElement getTamano() {
        return tamano;
    }

    public void setTamano(MenuElement tamano) {
        this.tamano = tamano;
    }

    public MenuElement getTienda() {
        return tienda;
    }

    public void setTienda(MenuElement tienda) {
        this.tienda = tienda;
    }

    public MenuElement getTipoorden() {
        return tipoorden;
    }

    public void setTipoorden(MenuElement tipoorden) {
        this.tipoorden = tipoorden;
    }

    public MenuElement getSubtipoorden() {
        return subtipoorden;
    }

    public void setSubtipoorden(MenuElement subtipoorden) {
        this.subtipoorden = subtipoorden;
    }

    public MenuElement getUsuario() {
        return usuario;
    }

    public void setUsuario(MenuElement usuario) {
        this.usuario = usuario;
    }
}