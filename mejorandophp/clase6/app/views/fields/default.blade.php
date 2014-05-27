<div class="form-group">
    {{ Form::label($name, $label) }}
    {{ $control }}
    @if ($error)
        <p class="error_message">{{ $error }}</p>
    @endif
</div>