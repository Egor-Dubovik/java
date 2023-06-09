<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="root" select="'Device'" />
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="/">
        <xsl:element name="{$root}">
            <xsl:apply-templates select="node()" />
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>