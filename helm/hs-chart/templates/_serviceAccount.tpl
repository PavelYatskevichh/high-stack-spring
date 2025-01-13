{{- define "hs-chart.serviceAccount" -}}
apiVersion: v1
kind: ServiceAccount
metadata:
  name: {{ .Values.serviceAccountName }}
  labels:
    {{- include "hs-chart.labels" . | nindent 4 }}
{{- end }}
