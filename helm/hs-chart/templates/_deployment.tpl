{{- define "hs-chart.deployment" -}}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ include "hs-chart.fullname" . }}
  labels:
    {{- include "hs-chart.labels" . | nindent 4 }}
spec:
  {{- if not (.Values.autoscaling).enabled }}
  replicas: {{ .Values.replicaCount }}
  {{- end }}
  selector:
    matchLabels:
      {{- include "hs-chart.selectorLabels" . | nindent 6 }}
  strategy: {}
  template:
    metadata:
      {{- with .Values.podAnnotations }}
      annotations:
        {{- toYaml . | nindent 8 }}
      {{- end }}
      labels:
        {{- include "hs-chart.labels" . | nindent 8 }}
        {{- with .Values.podLabels }}
        {{- toYaml . | nindent 8 }}
        {{- end }}
    spec:
      {{- if .Values.serviceAccountName }}
      serviceAccountName: {{ .Values.serviceAccountName }}
      {{- end }}
      restartPolicy: {{ default "Always" .Values.restartPolicy }}
      containers:
        - name: {{ include "hs-chart.name" . }}
          image: {{ .Values.image.name }}
          imagePullPolicy: {{ default "IfNotPresent" .Values.image.pullPolicy }}
          ports:
            - name: http
              containerPort: {{ default 8080 (.Values.container).port }}
              protocol: TCP
          envFrom:
            - configMapRef:
                name: {{ include "hs-chart.fullname" . }}-config
            - configMapRef:
                name: hs-spring-high-stack-facade-datasource-config
          env:
            {{- range $key, $value := (.Values.additional).env }}
            - name: {{ $key }}
              value: {{ $value | quote }}
            {{- end }}
          resources:
            {{- toYaml .Values.resources | nindent 12 }}
          {{- with .Values.volumeMounts }}
          volumeMounts:
            {{- toYaml . | nindent 12 }}
          {{- end }}
      {{- with .Values.volumes }}
      volumes:
        {{- toYaml . | nindent 8 }}
      {{- end }}
{{- end }}